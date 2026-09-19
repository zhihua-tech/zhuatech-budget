/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.budget.service;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.math.*;
import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class DomainInsightService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String,Object> analyze(InsightRequest req){
        Map<String,Object> result=new LinkedHashMap<>();
        BigDecimal available=req.budgetAmount().subtract(req.committedAmount()).subtract(req.actualAmount());
BigDecimal remaining=available.subtract(req.requestAmount());boolean approved=remaining.signum()>=0;
result.put("availableAmount",available);result.put("remainingAfterRequest",remaining);result.put("approved",approved);result.put("decision",approved?"ALLOW":"BLOCK_OVER_BUDGET");
        return result;
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private BigDecimal rate(long numerator,long denominator){return denominator==0?BigDecimal.ZERO:BigDecimal.valueOf(numerator).multiply(BigDecimal.valueOf(100)).divide(BigDecimal.valueOf(denominator),2,RoundingMode.HALF_UP);}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record InsightRequest(@DecimalMin("0.0") BigDecimal budgetAmount, @DecimalMin("0.0") BigDecimal committedAmount, @DecimalMin("0.0") BigDecimal actualAmount, @DecimalMin("0.0") BigDecimal requestAmount){}
}
