/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.budget.controller;
import cn.zhuatech.budget.common.ApiResponse;import cn.zhuatech.budget.service.BudgetTransferGovernanceService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/enterprise/budget")public class BudgetTransferGovernanceController{private final BudgetTransferGovernanceService service;/**
                                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                */
public BudgetTransferGovernanceController(BudgetTransferGovernanceService service){this.service=service;}/**
                                                                                                                                                                                                                                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                         */
@PostMapping("/transfer-governance")public ApiResponse<BudgetTransferGovernanceService.Assessment> assess(@Valid @RequestBody BudgetTransferGovernanceService.Request request){return ApiResponse.ok(service.assess(request));}}
