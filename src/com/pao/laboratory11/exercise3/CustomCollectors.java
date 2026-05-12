package com.pao.laboratory11.exercise3;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;

public class CustomCollectors {

    public static Collector<Transaction, ?, Snapshot> toSnapshot(int topN) {
        // Container mutabil intern folosit doar în timpul colectării
        class Agg {
            Map<String, Long> countryMap = new HashMap<>();
            Map<String, Long> channelMap = new HashMap<>();
            BigDecimal total = BigDecimal.ZERO;
            List<Transaction> all = new ArrayList<>();

            void accumulate(Transaction tx) {
                countryMap.merge(tx.getCountry(), 1L, Long::sum);
                channelMap.merge(tx.getChannel(), 1L, Long::sum);
                total = total.add(tx.getAmount());
                all.add(tx);
            }

            Agg combine(Agg other) {
                other.countryMap.forEach((k, v) -> countryMap.merge(k, v, Long::sum));
                other.channelMap.forEach((k, v) -> channelMap.merge(k, v, Long::sum));
                total = total.add(other.total);
                all.addAll(other.all);
                return this;
            }

            Snapshot finish() {
                List<Transaction> top = all.stream()
                        .sorted(Comparator.comparing(Transaction::getAmount).reversed()
                                .thenComparing(Transaction::getId))
                        .limit(topN)
                        .toList();
                return new Snapshot(countryMap, channelMap, total, top);
            }
        }

        return Collector.of(Agg::new, Agg::accumulate, Agg::combine, Agg::finish);
    }
}
