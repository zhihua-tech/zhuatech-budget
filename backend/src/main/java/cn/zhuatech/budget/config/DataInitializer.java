/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.budget.config;
import cn.zhuatech.budget.model.*;
import cn.zhuatech.budget.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;
@Configuration public class DataInitializer {
    @Bean CommandLineRunner seed(BusinessRecordRepository records,SystemSettingRepository settings){return args->{
        if(records.count()>0)return;
            settings.save(new SystemSetting("budgetYear","2027"));
    settings.save(new SystemSetting("currency","CNY"));
    settings.save(new SystemSetting("controlMode","超预算禁止提交"));
    settings.save(new SystemSetting("forecastCycle","每月滚动"));
            records.save(new BusinessRecord("BDG-20260826-001","PLAN","2027年度研发费用预算","研发中心","预算专员","待审批",new BigDecimal("5200000"),18,LocalDate.now().plusDays(15),"正常","人力与云资源预算已拆分"));
    records.save(new BusinessRecord("BDG-20260826-002","ADJUSTMENT","市场活动预算追加申请","市场中心","市场负责人","草稿",new BigDecimal("380000"),4,LocalDate.now().plusDays(4),"关注","需说明新增线索目标"));
    records.save(new BusinessRecord("BDG-20260826-003","CONTROL","实施外包费用预算占用","交付中心","财务BP","执行中",new BigDecimal("860000"),12,LocalDate.now().plusDays(-3),"正常","已占用年度预算的62%"));
    records.save(new BusinessRecord("BDG-20260826-004","FORECAST","三季度收入成本滚动预测","公司级","财务分析","已下达",new BigDecimal("16800000"),32,LocalDate.now().plusDays(7),"正常","已纳入最新销售预测"));
    };}
}
