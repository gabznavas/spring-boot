package io.github.gabznavas.btgpactual.orderms.controller.dto;

import java.util.List;
import java.util.Map;

public record ApiResponse<T>(
        Map<String, Object> summay,
        List<T> data,
        PaginationResponse pagination
) {

}
