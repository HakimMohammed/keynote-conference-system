package org.example.keynoteservice.records;

import java.util.List;

public record PagedResponse<T>(
        List<T> content,
        int currentPage,
        long totalItems,
        int totalPages
) {
}
