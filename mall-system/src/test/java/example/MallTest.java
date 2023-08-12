package example;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.example.MallAdminApplication;
import org.example.modules.product.entity.SkuStockEntity;
import org.example.modules.product.service.SkuStockService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest(classes = MallAdminApplication.class)
public class MallTest {

    @Resource
    private SkuStockService skuStockService;

    @Test
    public void testUser() {
        LambdaQueryWrapper<SkuStockEntity> skuStockEntityLambdaQueryWrapper = new LambdaQueryWrapper<>();
        skuStockEntityLambdaQueryWrapper.isNotNull(SkuStockEntity::getId);
        List<SkuStockEntity> skuStockEntityList = skuStockService.list(skuStockEntityLambdaQueryWrapper);
        skuStockEntityList.forEach(skuStockEntity -> skuStockEntity.setPic("2023-07/31/72eab2ef5301448aa970e8443d52684e.png"));
        skuStockService.updateBatchById(skuStockEntityList);

    }
}
