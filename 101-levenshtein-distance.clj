(fn f
  ([c1 c2] (cond
             (or (empty? c1) (empty? c2)) (max (count c1) (count c2))
             (< (count c2) (count c1)) (recur c2 c1)
             :else (let [compute-dist (fn [coll]
                                        (let [coll2 (concat (cons [-1 -1] coll) [[(count c1) (count c2)]])]
                                          (apply + (for [idx (range (dec (count coll2)))]
                                                     (let [itm1 (nth coll2 idx)
                                                           itm2 (nth coll2 (inc idx))]
                                                       (max (- (first itm2) (first itm1) 1)
                                                            (- (second itm2) (second itm1) 1)))))))
                         n (count c1)
                         rng (range n)
                         coll-coll (map (fn [c-c]
                                          (map (fn [coll]
                                                 (let [coll-set (set coll)]
                                                   (remove #(get coll-set %1) rng)))
                                               c-c))
                                        (take n (iterate (fn [coll]
                                                           (for [c coll
                                                                 y rng
                                                                 :when (every? #(> y %1) c)]
                                                             (conj c y)))
                                                         [[]])))]
                     (loop [c-coll coll-coll]
                       (let [resu (->> (map (fn [coll] [coll (f c1 c2 coll)]) (first c-coll))
                                       (filter #(= (count (first %1)) (count (second %1)))))
                             dist-resu (->> (map #(compute-dist (second %1)) resu)
                                            (sort compare))]
                         (cond
                           (empty? c-coll) 0
                           (empty? dist-resu) (recur (rest c-coll))
                           :else (first dist-resu)))))))
  ([c1 c2 idx-coll] (filter #(>= (second %) 0)
                            (:coll (reduce (fn [resu itm]
                                             (let [c1-idx (:c1-idx resu)
                                                   c1-dist (- itm c1-idx 1)
                                                   c2-idx (:c2-idx resu)
                                                   coll (:coll resu)
                                                   idx (first (sort #(compare (Math/abs (- %1 c2-idx c1-dist)) (Math/abs (- %2 c2-idx c1-dist)))
                                                                    (filter #(>= % c2-idx)
                                                                            (keep-indexed #(if (= %2 (nth c1 itm)) %1) c2))))]
                                               (assoc resu :c1-idx (or (and idx itm)
                                                                       c1-idx)
                                                           :c2-idx (or (and idx
                                                                            (inc idx))
                                                                       c2-idx)
                                                           :coll (conj coll [itm (or idx -1)]))))
                                           {:c1-idx -1
                                            :c2-idx -1
                                            :coll   []}
                                           idx-coll)))))