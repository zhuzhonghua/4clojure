(defn mm[a-set b-set]
  (set (reduce (fn [res v]
                 (concat res (map #(vector % v) a-set)))
               '() b-set)))

;;(println 1)
(println (mm #{1 2 3} #{4 5}))
;(def a #{1 2 3})
;(def b #{4 5})
;;(println (set (for [x a y b]
;;                [x y])))

;;(println
;; (map #(vector 1 %) a))

;(defn cc[a v]
;  (map #(vector % v) a))

;(defn bb[a b]
;;  (set (reduce (fn [res v]
;;            (concat res (cc a v)))
;;          '() b)))

;;(println
;; (bb a b))
;reduce conj a
;    map [c %]
