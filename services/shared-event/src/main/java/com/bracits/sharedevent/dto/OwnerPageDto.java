package com.bracits.sharedevent.dto;

import java.util.List;

public record OwnerPageDto(
        List<OwnerResponseDto> content,
        int pageNumber,
        int pageSize,
        long totalElements,
        int totalPages,
        boolean last
) {}