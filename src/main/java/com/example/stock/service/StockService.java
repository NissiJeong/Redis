package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    //@Transactional // 우리가 만든 클래스를 매핑할 클래스를 새로 만들어서 실행하게 됨. 트랜잭션이 열리고 닫히기 전(업데이트 되기전) 다른 스레드가 해당 메서드를 호출하게 되면 동시성 이슈가 해결되지 않음
    // synchronized 이용할때는 @Transactional 어노테이션을 사용하면 안됨.
    public synchronized void decrease(Long id, Long quantity) {
        // Stock 조회
        // 재고 감소
        // 갱신된 값을 저장

        Stock stock = stockRepository.findById(id).orElseThrow();
        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
