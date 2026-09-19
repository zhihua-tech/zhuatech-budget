/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.budget.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.http.HttpStatus;import org.springframework.stereotype.Service;import org.springframework.web.server.ResponseStatusException;import java.math.*;import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class EnterpriseBudgetService {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public ControlResult reserve(@Valid ControlRequest req){
  BigDecimal available=req.approvedBudget().subtract(req.committed()).subtract(req.actual());Set<String> ids=new HashSet<>();List<ReservationResult> results=new ArrayList<>();
  for(var item:req.requests()){
   if(!ids.add(item.requestNo()))throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"预算申请编号重复: "+item.requestNo());
   boolean approved=available.compareTo(item.amount())>=0||item.allowOverride();
   String decision=approved?(available.compareTo(item.amount())>=0?"RESERVED":"OVERRIDE_REQUIRED"):"BLOCKED";
   if(approved)available=available.subtract(item.amount());
   results.add(new ReservationResult(item.requestNo(),item.amount(),money(available),decision));
  }
  return new ControlResult(req.organizationCode(),req.period(),money(req.approvedBudget()),money(available),results,
    results.stream().anyMatch(r->"BLOCKED".equals(r.decision()))?"PARTIAL":"RESERVED");
 }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 private BigDecimal money(BigDecimal v){return v.setScale(2,RoundingMode.HALF_UP);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record ControlRequest(@NotBlank String organizationCode,@NotBlank @Pattern(regexp="\\d{4}-(0[1-9]|1[0-2])") String period,
  @NotNull @DecimalMin("0") BigDecimal approvedBudget,@NotNull @DecimalMin("0") BigDecimal committed,@NotNull @DecimalMin("0") BigDecimal actual,
  @NotEmpty List<@Valid Reservation> requests){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Reservation(@NotBlank String requestNo,@NotNull @DecimalMin("0.01") BigDecimal amount,boolean allowOverride){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record ReservationResult(String requestNo,BigDecimal amount,BigDecimal availableAfter,String decision){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record ControlResult(String organizationCode,String period,BigDecimal approvedBudget,BigDecimal availableAfter,List<ReservationResult> reservations,String decision){}
}
