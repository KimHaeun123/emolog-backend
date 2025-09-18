package com.emolog.controller;

import com.emolog.dto.diary.DiaryRequest;
import com.emolog.dto.diary.DiaryResponse;
import com.emolog.service.DiaryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diaries")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // 일기 작성
    @PostMapping
    public ResponseEntity<DiaryResponse> createDiary(@RequestBody DiaryRequest request) {
        return ResponseEntity.ok(diaryService.createDiary(request));
    }

    // 일기 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<DiaryResponse> getDiary(@PathVariable Long id) {
        return ResponseEntity.ok(diaryService.getDiary(id));
    }

    // 일기 전체 조회
    @GetMapping
    public ResponseEntity<List<DiaryResponse>> getAllDiaries() {
        return ResponseEntity.ok(diaryService.getAllDiaries());
    }

    // 일기 수정
    @PutMapping("/{id}")
    public ResponseEntity<DiaryResponse> updateDiary(@PathVariable Long id, @RequestBody DiaryRequest request) {
        return ResponseEntity.ok(diaryService.updateDiary(id, request));
    }

    // 일기 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Long id) {
        diaryService.deleteDiary(id);
        return ResponseEntity.noContent().build();
    }
}
