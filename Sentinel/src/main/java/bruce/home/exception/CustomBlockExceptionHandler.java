package bruce.home.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 取代預設的錯誤訊息
 */
@Component
public class CustomBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, BlockException e)
            throws Exception {
        res.setStatus(HttpStatus.SERVICE_UNAVAILABLE.value());
        if (e instanceof FlowException) {
            System.out.println("FlowException 限流，資源信息：" + JSON.toJSONString(e.getRule()));
            res.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            write(res, "我錯了");
        } else if (e instanceof DegradeException) {
            System.out.println("DegradeException 降級，資源信息：" + JSON.toJSONString(e.getRule()));
        } else if (e instanceof ParamFlowException) {
            ParamFlowException ex = (ParamFlowException) e;
            System.out.println("ParamFlowException 熱點限流，資源名=" + ex.getResourceName() + "，參數=" + ex.getLimitParam() + "，資源信息=" + JSON.toJSONString(ex.getRule()));
        } else if (e instanceof AuthorityException) {
            System.out.println("AuthorityException 授權規則，資源信息：" + JSON.toJSONString(e.getRule()));
        } else if (e instanceof SystemBlockException) {
            SystemBlockException systemBlockException = (SystemBlockException) e;
            System.out.println("SystemBlockException，資源名：" + systemBlockException.getResourceName() + "，資源類型：" + systemBlockException.getRuleLimitApp());
        }
    }

    public static void write(HttpServletResponse res, Object o) throws Exception {
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();
        out.println(o.toString());
        out.flush();
        out.close();
    }
}
