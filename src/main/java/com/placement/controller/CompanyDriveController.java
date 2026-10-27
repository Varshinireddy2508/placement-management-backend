package com.placement.controller;

import com.placement.entity.CompanyDrive;
import com.placement.service.CompanyDriveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drives")
public class CompanyDriveController {

    private final CompanyDriveService driveService;

    public CompanyDriveController(CompanyDriveService driveService) {
        this.driveService = driveService;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDrive>> getAllDrives() {
        return ResponseEntity.ok(driveService.getAllDrives());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyDrive> getDriveById(@PathVariable Long id) {
        return ResponseEntity.ok(driveService.getDriveById(id));
    }

    @PostMapping
    public ResponseEntity<CompanyDrive> createDrive(@RequestBody CompanyDrive drive) {
        CompanyDrive created = driveService.createDrive(drive);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDrive> updateDrive(@PathVariable Long id, @RequestBody CompanyDrive drive) {
        return ResponseEntity.ok(driveService.updateDrive(id, drive));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrive(@PathVariable Long id) {
        driveService.deleteDrive(id);
        return ResponseEntity.noContent().build();
    }
}
