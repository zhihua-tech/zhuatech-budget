/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.budget;
import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;import org.springframework.http.MediaType;import org.springframework.test.web.servlet.MockMvc;import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc class EnterpriseBudgetApiTests {@Autowired MockMvc mvc;
 @Test void reservationsConsumeAvailableBudgetInOrder() throws Exception {mvc.perform(post("/api/enterprise/budget/reserve").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"organizationCode":"ZH-SH","period":"2026-08","approvedBudget":10000,"committed":2000,"actual":3000,"requests":[{"requestNo":"R1","amount":4000,"allowOverride":false},{"requestNo":"R2","amount":2000,"allowOverride":false}]}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.reservations[0].decision").value("RESERVED")).andExpect(jsonPath("$.data.reservations[1].decision").value("BLOCKED")).andExpect(jsonPath("$.data.availableAfter").value(1000.0));}
 @Test void duplicateRequestNumbersAreRejected() throws Exception {mvc.perform(post("/api/enterprise/budget/reserve").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"organizationCode":"ZH-SH","period":"2026-08","approvedBudget":10000,"committed":0,"actual":0,"requests":[{"requestNo":"R1","amount":10,"allowOverride":false},{"requestNo":"R1","amount":10,"allowOverride":false}]}
 """)).andExpect(status().isBadRequest());}
}
