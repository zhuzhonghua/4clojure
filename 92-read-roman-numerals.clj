(defn mm [para]
  (let [value-map {\I 1 \V 5 \X 10 \L 50 \C 100 \D 500 \M 1000}]
    (letfn [(t1 [s]
              (if (< (count s) 2)
                (if (= 0 (count s)) [] [s])
                (if (< (value-map (first s)) (value-map (second s)))
                  (cons (subs s 0 2) (t1 (subs s 2)))
                  (cons (subs s 0 1) (t1 (subs s 1))))))
            (t2 [s]
              (reduce + (map (fn [str]
                               (if (and (> (count str) 1) (< (value-map (first str)) (value-map (second str))))
                                 (- (value-map (second str)) (value-map (first str)))
                                 (reduce + (map #(value-map %) str))))
                             s)))]
      (t2 (t1 para)))))
;(println (count (t1 "XIV")))
(println (mm "XIV"))
;(println (t2 (t1 "XIV")))
;(println (t2 (t1 "DCCCXXVII")))
;(println (t2 (t1 "MMMCMXCIX")))
;(println (t2 (t1 "XLVIII")))
;(println (t1 "XIV"))
