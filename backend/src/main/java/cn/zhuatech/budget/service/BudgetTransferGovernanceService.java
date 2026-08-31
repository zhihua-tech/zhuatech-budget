/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.budget.service;
import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.util.*;
@Service
public class BudgetTransferGovernanceService{
 public Assessment assess(Request r){List<String>b=new ArrayList<>();List<String>a=new ArrayList<>();
  if(!r.periodOpen())b.add("预算期间已关闭");if(r.transferAmountCents()>r.sourceAvailableCents())b.add("来源预算可用额不足");
  if(!r.requesterApproverSeparated())b.add("申请人与审批人未实现职责分离");if(!r.sourceOwnerApproved())a.add("取得来源责任中心负责人批准");
  if(r.crossCostCenter()&&!r.targetOwnerApproved())a.add("取得目标责任中心负责人批准");if(!r.financeApproved())a.add("取得财务预算管理员批准");
  if(r.materialPlanImpact()&&!r.annualPlanImpactReviewed())a.add("复核对年度经营计划和 KPI 的影响");
  Decision d=!b.isEmpty()?Decision.HOLD:!a.isEmpty()?Decision.APPROVAL_REQUIRED:Decision.POST;
  return new Assessment(r.transferNo(),r.transferAmountCents(),d,List.copyOf(b),List.copyOf(a));}
 public record Request(@NotBlank String transferNo,@Min(1)long transferAmountCents,@Min(0)long sourceAvailableCents,
  boolean periodOpen,boolean requesterApproverSeparated,boolean sourceOwnerApproved,boolean crossCostCenter,
  boolean targetOwnerApproved,boolean financeApproved,boolean materialPlanImpact,boolean annualPlanImpactReviewed){}
 public record Assessment(String transferNo,long transferAmountCents,Decision decision,List<String> blockers,List<String> actions){}
 public enum Decision{POST,APPROVAL_REQUIRED,HOLD}
}
