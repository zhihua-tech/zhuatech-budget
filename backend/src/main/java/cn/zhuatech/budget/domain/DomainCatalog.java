/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.budget.domain;
import org.springframework.stereotype.Component;
import java.util.*;
@Component
public class DomainCatalog {
    private final Map<String,WorkflowAction> actions=new LinkedHashMap<>();
    public DomainCatalog(){
        actions.put("SUBMIT", new WorkflowAction("SUBMIT", "提交预算", List.of("草稿"), "待审批"));
actions.put("APPROVE", new WorkflowAction("APPROVE", "审批下达", List.of("待审批"), "已下达"));
actions.put("EXECUTE", new WorkflowAction("EXECUTE", "开始执行", List.of("已下达"), "执行中"));
actions.put("CLOSE", new WorkflowAction("CLOSE", "年度关闭", List.of("执行中"), "已关闭"));
    }
    public String systemName(){return "知华科技全面预算管理系统";}
    public String scene(){return "年度预算、滚动预测、预算调整、执行控制和偏差分析管理";}
    public String initialStatus(){return "草稿";}
    public String partyLabel(){return "预算组织";} public String amountLabel(){return "预算金额";}
    public String quantityLabel(){return "预算科目";} public String dueLabel(){return "编制截止日";}
    public List<ModuleDefinition> modules(){return List.of(
        new ModuleDefinition("PLAN","预算编制","按组织、科目和期间编制年度预算"),
    new ModuleDefinition("ADJUSTMENT","预算调整","申请追加、调剂、冻结和释放预算"),
    new ModuleDefinition("CONTROL","执行控制","占用、消耗和超预算事项在线校验"),
    new ModuleDefinition("FORECAST","滚动预测","结合实际执行更新全年预测")
    );}
    public Map<String,WorkflowAction> actions(){return Collections.unmodifiableMap(actions);}
    public record ModuleDefinition(String code,String name,String description){}
    public record WorkflowAction(String code,String label,List<String> from,String to){}
}
