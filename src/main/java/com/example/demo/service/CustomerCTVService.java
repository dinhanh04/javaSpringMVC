package com.example.demo.service;

import com.example.demo.domain.CustomerCTV;
import com.example.demo.repository.CustomerCTVRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerCTVService {
    private final CustomerCTVRepository customerCTVRepository;

    public CustomerCTVService(CustomerCTVRepository customerCTVRepository) {
        this.customerCTVRepository = customerCTVRepository;
    }

    public List<CustomerCTV> importFromExcel(MultipartFile file) throws IOException {
        List<CustomerCTV> customers = new ArrayList<>();
        
        try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
            Sheet sheet = workbook.getSheetAt(0);
            
            int startRow = 1;
            for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                
                CustomerCTV customer = parseRowToCustomer(row);
                if (customer != null && customer.getEmail() != null && !customer.getEmail().trim().isEmpty()) {
                    customers.add(customer);
                }
            }
        }
        
        return saveAllCustomers(customers);
    }

    private CustomerCTV parseRowToCustomer(Row row) {
        try {
            CustomerCTV customer = new CustomerCTV();
            
            customer.setHoTen(getCellValueAsString(row.getCell(0)));
            customer.setEmail(getCellValueAsString(row.getCell(1)));
            customer.setSoDienThoai(getCellValueAsString(row.getCell(2)));
            
            LocalDate ngayGiaNhap = getCellValueAsDate(row.getCell(3));
            if (ngayGiaNhap == null) {
                return null;
            }
            customer.setNgayGiaNhap(ngayGiaNhap);
            
            customer.setDiaChi(getCellValueAsString(row.getCell(4)));
            customer.setTaiKhoanNganHang(getCellValueAsString(row.getCell(5)));
            customer.setTenNganHang(getCellValueAsString(row.getCell(6)));
            customer.setMaSoThue(getCellValueAsString(row.getCell(7)));
            
            BigDecimal tyLeHoaHong = getCellValueAsBigDecimal(row.getCell(8));
            customer.setTyLeHoaHong(tyLeHoaHong != null ? tyLeHoaHong : new BigDecimal("0.05"));
            
            return customer;
        } catch (Exception e) {
            return null;
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return null;
        }
        
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    double numericValue = cell.getNumericCellValue();
                    if (numericValue == (long) numericValue) {
                        return String.valueOf((long) numericValue);
                    } else {
                        return String.valueOf(numericValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return null;
        }
    }

    private LocalDate getCellValueAsDate(Cell cell) {
        if (cell == null) {
            return null;
        }
        
        try {
            switch (cell.getCellType()) {
                case STRING:
                    String dateString = cell.getStringCellValue().trim();
                    return LocalDate.parse(dateString);
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        Date date = cell.getDateCellValue();
                        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    } else {
                        return null;
                    }
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private BigDecimal getCellValueAsBigDecimal(Cell cell) {
        if (cell == null) {
            return null;
        }
        
        try {
            switch (cell.getCellType()) {
                case NUMERIC:
                    return BigDecimal.valueOf(cell.getNumericCellValue());
                case STRING:
                    String value = cell.getStringCellValue().trim();
                    if (value.isEmpty()) {
                        return null;
                    }
                    return new BigDecimal(value);
                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private List<CustomerCTV> saveAllCustomers(List<CustomerCTV> customers) {
        List<CustomerCTV> savedCustomers = new ArrayList<>();
        
        for (CustomerCTV customer : customers) {
            Optional<CustomerCTV> existingCustomer = customerCTVRepository.findByEmail(customer.getEmail());
            
            if (existingCustomer.isPresent()) {
                CustomerCTV existing = existingCustomer.get();
                existing.setHoTen(customer.getHoTen());
                existing.setSoDienThoai(customer.getSoDienThoai());
                existing.setNgayGiaNhap(customer.getNgayGiaNhap());
                existing.setDiaChi(customer.getDiaChi());
                existing.setTaiKhoanNganHang(customer.getTaiKhoanNganHang());
                existing.setTenNganHang(customer.getTenNganHang());
                existing.setMaSoThue(customer.getMaSoThue());
                existing.setTyLeHoaHong(customer.getTyLeHoaHong());
                savedCustomers.add(customerCTVRepository.save(existing));
            } else {
                savedCustomers.add(customerCTVRepository.save(customer));
            }
        }
        
        return savedCustomers;
    }
}



