package com.example.demo.controller;

import com.example.demo.domain.CustomerCTV;
import com.example.demo.service.CustomerCTVService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer-ctv")
public class CustomerCTVController {
    private final CustomerCTVService customerCTVService;

    public CustomerCTVController(CustomerCTVService customerCTVService) {
        this.customerCTVService = customerCTVService;
    }

    @PostMapping("/import-excel")
    public ResponseEntity<Map<String, Object>> importExcel(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            if (file.isEmpty()) {
                response.put("success", false);
                response.put("message", "File không được để trống");
                return ResponseEntity.badRequest().body(response);
            }
            
            String fileName = file.getOriginalFilename();
            if (fileName == null || (!fileName.endsWith(".xlsx") && !fileName.endsWith(".xls"))) {
                response.put("success", false);
                response.put("message", "File phải là định dạng Excel (.xlsx hoặc .xls)");
                return ResponseEntity.badRequest().body(response);
            }
            
            List<CustomerCTV> importedCustomers = customerCTVService.importFromExcel(file);
            
            response.put("success", true);
            response.put("message", "Import thành công " + importedCustomers.size() + " bản ghi");
            response.put("count", importedCustomers.size());
            response.put("data", importedCustomers);
            
            return ResponseEntity.ok(response);
            
        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "Lỗi khi đọc file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "Lỗi khi xử lý file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}

