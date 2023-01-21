package hw26.repository.hibernate;

import hw26.entity.Goods;
import hw26.repository.GoodsRepository;

public class GoodsRepositoryImpl extends AbstractHibernateRepository<Goods> implements GoodsRepository {
    protected void init () {
        aClass = Goods.class;
    }
}
