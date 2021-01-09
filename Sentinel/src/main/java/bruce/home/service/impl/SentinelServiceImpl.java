package bruce.home.service.impl;

import bruce.home.service.SentinelService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class SentinelServiceImpl implements SentinelService {
    @Override
    @SentinelResource(value = "abcdef", blockHandler = "chainHandler")
    public String testChain() {
        return "test chain";
    }

    public void chainHandler(BlockException ex) {
        System.out.println("chainHandler exception" + ex.getMessage());
    }

}
