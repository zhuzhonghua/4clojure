(fn [n coll]
   (let [in (mod n (count coll))]
     (concat (drop in coll) (take in coll))))