(fn dist [c1 c2]
  (let [find-match (fn [item c2 c2-match-idx]
                     (keep-indexed #(if (and (= %2 item)
                                             (> %1 c2-match-idx))
                                      %1) c2))
        find-match-dist (fn [c2-idx-set c2-match-idx c1-dist]
                          (->> c2-idx-set
                               (map #(list % (Math/abs (- % c2-match-idx c1-dist 1))))
                               (sort-by last)
                               first))
        check-item (fn [c2-idx-dist c1-idx c1-match-idx c2-match-idx c1 c2]
                     (loop [c1-idx-next (inc c1-idx)
                            c1-c2-target [c1-idx (first c2-idx-dist)]
                            c2-idx-dist-target c2-idx-dist]
                       (if (>= c1-idx-next (count c1))
                         c1-c2-target
                         (let [c2-idx-set-next (find-match (nth c1 c1-idx-next)
                                                           c2
                                                           c2-match-idx)
                               c2-idx-dist-next (find-match-dist c2-idx-set-next c2-match-idx (- c1-idx-next c1-match-idx 1))]
                           (if (and c2-idx-dist-next
                                    (<= (first c2-idx-dist-next) (first c2-idx-dist-target))
                                    (< (last c2-idx-dist-next) (last c2-idx-dist-target)))
                             (recur (inc c1-idx-next)
                                    [c1-idx-next (first c2-idx-dist-next)]
                                    c2-idx-dist-next)
                             (recur (inc c1-idx-next)
                                    c1-c2-target
                                    c2-idx-dist-target))))))
        t1-1 (fn [c1 c2]
               (loop [match []
                      c1-idx 0]
                 (if (>= c1-idx (count c1))
                   match
                   (let [item (nth c1 c1-idx)
                         pre-match (or (last match) [-1 -1])
                         c1-match-idx (first pre-match)
                         c2-match-idx (second pre-match)
                         c1-dist (- c1-idx c1-match-idx 1)
                         c2-idx-set (find-match item c2 c2-match-idx)
                         c2-idx-dist (find-match-dist c2-idx-set c2-match-idx c1-dist)]
                     (cond
                       (and c2-idx-dist (< c1-match-idx 0)) (recur (conj match [c1-idx (first c2-idx-dist)])
                                                                   (inc c1-idx))
                       (and c2-idx-dist) (let [result (check-item c2-idx-dist c1-idx c1-match-idx c2-match-idx c1 c2)]
                                           (recur (conj match result)
                                                  (inc (first result))))
                       :else (recur match (inc c1-idx)))))))
        all-dist (concat [[-1 -1]] (t1-1 c1 c2) [[(count c1) (count c2)]])]
    (apply + (map (fn [pre next]
                    (max (- (first next) (first pre) 1)
                         (- (second next) (second pre) 1)))
                  all-dist
                  (rest all-dist)))))