/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.budget.controller;
import cn.zhuatech.budget.common.ApiResponse;import cn.zhuatech.budget.service.EnterpriseBudgetService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/budget") public class EnterpriseBudgetController {private final EnterpriseBudgetService service;public EnterpriseBudgetController(EnterpriseBudgetService service){this.service=service;}@PostMapping("/reserve") ApiResponse<EnterpriseBudgetService.ControlResult> reserve(@Valid @RequestBody EnterpriseBudgetService.ControlRequest request){return ApiResponse.ok(service.reserve(request));}}
