package com.volvo.emsp.common;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    private List<T> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalRows;
    private Integer totalPages;

    public static <T> PageResult<T> of(List<T> content, int pageNumber, int pageSize, int totalRows, int totalRowsPages) {
        return new PageResult<>(content, pageNumber, pageSize, totalRows, totalRowsPages);
    }

    public static <T> PageResult<T> mapFromSpringPage(Page<T> page) {
        return PageResult.of(
                page.getContent(),
                page.getNumber() + 1,
                page.getSize(),
                (int) page.getTotalElements(),
                page.getTotalPages()
        );
    }

    public <U> PageResult<U> map(@NonNull Function<? super T, ? extends U> converter) {
        return new PageResult<>(getConvertedContent(converter), pageNumber, pageSize, totalRows, totalPages);
    }

    private <U> List<U> getConvertedContent(@NonNull Function<? super T, ? extends U> converter) {
        return this.content.stream().map(converter).collect(Collectors.toList());
    }

    public static <T> PageResult<T> getPageResult(Integer pageNumber, Integer pageSize, List<T> labelList) {
        int totalPages;
        if (labelList.isEmpty()) {
            return PageResult.of(labelList, pageNumber, pageSize, 0, 1);
        }
        if (labelList.size() <= pageSize) {
            totalPages = 1;
        } else {
            totalPages = (labelList.size() / pageSize);
            if (labelList.size() % pageSize != 0) {
                totalPages++;
            }
        }
        pageNumber = Math.min(pageNumber, totalPages);
        final int currentPageEndIndex = Math.min(pageNumber * pageSize, labelList.size());
        final List<T> currentPageContent = labelList.subList((pageNumber - 1) * pageSize, currentPageEndIndex);
        return PageResult.of(currentPageContent, pageNumber, pageSize, labelList.size(), totalPages);
    }
}
