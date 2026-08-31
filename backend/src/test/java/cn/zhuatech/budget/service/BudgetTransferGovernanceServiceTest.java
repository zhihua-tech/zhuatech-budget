/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.budget.service;
import org.junit.jupiter.api.Test;import static org.assertj.core.api.Assertions.assertThat;
class BudgetTransferGovernanceServiceTest{private final BudgetTransferGovernanceService service=new BudgetTransferGovernanceService();
 @Test void postsControlledTransfer(){var r=service.assess(new BudgetTransferGovernanceService.Request("BT-001",500000,800000,true,true,true,true,true,true,true,true));assertThat(r.decision()).isEqualTo(BudgetTransferGovernanceService.Decision.POST);}
 @Test void holdsInvalidTransfer(){var r=service.assess(new BudgetTransferGovernanceService.Request("BT-002",900000,200000,false,false,false,true,false,false,true,false));assertThat(r.decision()).isEqualTo(BudgetTransferGovernanceService.Decision.HOLD);assertThat(r.blockers()).hasSize(3);assertThat(r.actions()).hasSize(4);}}
