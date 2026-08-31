/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.budget.controller;
import cn.zhuatech.budget.common.ApiResponse;import cn.zhuatech.budget.service.BudgetTransferGovernanceService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/enterprise/budget")public class BudgetTransferGovernanceController{private final BudgetTransferGovernanceService service;public BudgetTransferGovernanceController(BudgetTransferGovernanceService service){this.service=service;}@PostMapping("/transfer-governance")public ApiResponse<BudgetTransferGovernanceService.Assessment> assess(@Valid @RequestBody BudgetTransferGovernanceService.Request request){return ApiResponse.ok(service.assess(request));}}
