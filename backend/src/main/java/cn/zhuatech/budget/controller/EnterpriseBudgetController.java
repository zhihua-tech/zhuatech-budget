/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.budget.controller;
import cn.zhuatech.budget.common.ApiResponse;import cn.zhuatech.budget.service.EnterpriseBudgetService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/enterprise/budget") public class EnterpriseBudgetController {private final EnterpriseBudgetService service;/**
                                                                                                                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                  */
public EnterpriseBudgetController(EnterpriseBudgetService service){this.service=service;}/**
                                                                                                                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                           */
@PostMapping("/reserve") ApiResponse<EnterpriseBudgetService.ControlResult> reserve(@Valid @RequestBody EnterpriseBudgetService.ControlRequest request){return ApiResponse.ok(service.reserve(request));}}
