package com.emolog.service;

import com.emolog.dto.diary.DiaryRequest;
import com.emolog.dto.diary.DiaryResponse;
import com.emolog.entity.Diary;
import com.emolog.repository.DiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    // 일기 작성
    public DiaryResponse createDiary(DiaryRequest request) {
        Diary diary = new Diary();
        diary.setTitle(request.getTitle());
        diary.setContent(request.getContent());
        Diary saved = diaryRepository.save(diary);
        return toResponse(saved);
    }

    // 일기 단건 조회
    public DiaryResponse getDiary(Long id) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diary not found"));
        return toResponse(diary);
    }

    // 전체 목록 조회
    public List<DiaryResponse> getAllDiaries() {
        return diaryRepository.findAll()
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // 일기 수정
    public DiaryResponse updateDiary(Long id, DiaryRequest request) {
        Diary diary = diaryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diary not found"));
        diary.setTitle(request.getTitle());
        diary.setContent(request.getContent());
        Diary updated = diaryRepository.save(diary);
        return toResponse(updated);
    }

    // 일기 삭제
    public void deleteDiary(Long id) {
        diaryRepository.deleteById(id);
    }

    // DTO 변환
    private DiaryResponse toResponse(Diary diary) {
        DiaryResponse response = new DiaryResponse();
        response.setId(diary.getId());
        response.setTitle(diary.getTitle());
        response.setContent(diary.getContent());
        response.setCreatedAt(diary.getCreatedAt());
        response.setUpdatedAt(diary.getUpdatedAt());
        return response;
    }
}
