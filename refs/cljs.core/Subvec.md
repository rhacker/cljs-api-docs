## cljs.core/Subvec



 <table border="1">
<tr>
<td>type</td>
<td><a href="https://github.com/cljsinfo/cljs-api-docs/tree/0.0-927"><img valign="middle" alt="[+] 0.0-927" title="Added in 0.0-927" src="https://img.shields.io/badge/+-0.0--927-lightgrey.svg"></a> </td>
</tr>
</table>


 <samp>
(__Subvec.__ meta v start end __hash)<br>
</samp>

---







Source code @ [github](https://github.com/clojure/clojurescript/blob/r1859/src/cljs/cljs/core.cljs#L3420-L3499):

```clj
(deftype Subvec [meta v start end ^:mutable __hash]
  Object
  (toString [coll]
    (pr-str* coll))

  IWithMeta
  (-with-meta [coll meta] (build-subvec meta v start end __hash))

  IMeta
  (-meta [coll] meta)

  IStack
  (-peek [coll]
    (-nth v (dec end)))
  (-pop [coll]
    (if (== start end)
      (throw (js/Error. "Can't pop empty vector"))
      (build-subvec meta v start (dec end) nil)))

  ICollection
  (-conj [coll o]
    (build-subvec meta (-assoc-n v end o) start (inc end) nil))

  IEmptyableCollection
  (-empty [coll] (with-meta cljs.core.PersistentVector/EMPTY meta))

  ISequential
  IEquiv
  (-equiv [coll other] (equiv-sequential coll other))

  IHash
  (-hash [coll] (caching-hash coll hash-coll __hash))

  ISeqable
  (-seq [coll]
    (let [subvec-seq (fn subvec-seq [i]
                       (when-not (== i end)
                         (cons (-nth v i)
                               (lazy-seq
                                (subvec-seq (inc i))))))]
      (subvec-seq start)))

  ICounted
  (-count [coll] (- end start))

  IIndexed
  (-nth [coll n]
    (if (or (neg? n) (<= end (+ start n)))
      (vector-index-out-of-bounds n (- end start))
      (-nth v (+ start n))))
  (-nth [coll n not-found]
    (if (or (neg? n) (<= end (+ start n)))
      not-found
      (-nth v (+ start n) not-found)))

  ILookup
  (-lookup [coll k] (-nth coll k nil))
  (-lookup [coll k not-found] (-nth coll k not-found))

  IAssociative
  (-assoc [coll key val]
    (let [v-pos (+ start key)]
      (build-subvec meta (assoc v v-pos val)
               start (max end (inc v-pos))
               nil)))

  IVector
  (-assoc-n [coll n val] (-assoc coll n val))

  IReduce
  (-reduce [coll f]
    (ci-reduce coll f))
  (-reduce [coll f start]
    (ci-reduce coll f start))

  IFn
  (-invoke [coll k]
    (-nth coll k))
  (-invoke [coll k not-found]
    (-nth coll k not-found)))
```

<!--
Repo - tag - source tree - lines:

 <pre>
clojurescript @ r1859
└── src
    └── cljs
        └── cljs
            └── <ins>[core.cljs:3420-3499](https://github.com/clojure/clojurescript/blob/r1859/src/cljs/cljs/core.cljs#L3420-L3499)</ins>
</pre>

-->

---



###### External doc links:

[`cljs.core/Subvec` @ crossclj](http://crossclj.info/fun/cljs.core.cljs/Subvec.html)<br>

---

 <table>
<tr><td>
<img valign="middle" align="right" width="48px" src="http://i.imgur.com/Hi20huC.png">
</td><td>
Created for the upcoming ClojureScript website.<br>
[edit here] | [learn how]
</td></tr></table>

[edit here]:https://github.com/cljsinfo/cljs-api-docs/blob/master/cljsdoc/cljs.core/Subvec.cljsdoc
[learn how]:https://github.com/cljsinfo/cljs-api-docs/wiki/cljsdoc-files

<!--

This information was too distracting to show to readers, but I'll leave it
commented here since it is helpful to:

- pretty-print the data used to generate this document
- and show how to retrieve that data



The API data for this symbol:

```clj
{:ns "cljs.core",
 :name "Subvec",
 :type "type",
 :signature ["[meta v start end __hash]"],
 :source {:code "(deftype Subvec [meta v start end ^:mutable __hash]\n  Object\n  (toString [coll]\n    (pr-str* coll))\n\n  IWithMeta\n  (-with-meta [coll meta] (build-subvec meta v start end __hash))\n\n  IMeta\n  (-meta [coll] meta)\n\n  IStack\n  (-peek [coll]\n    (-nth v (dec end)))\n  (-pop [coll]\n    (if (== start end)\n      (throw (js/Error. \"Can't pop empty vector\"))\n      (build-subvec meta v start (dec end) nil)))\n\n  ICollection\n  (-conj [coll o]\n    (build-subvec meta (-assoc-n v end o) start (inc end) nil))\n\n  IEmptyableCollection\n  (-empty [coll] (with-meta cljs.core.PersistentVector/EMPTY meta))\n\n  ISequential\n  IEquiv\n  (-equiv [coll other] (equiv-sequential coll other))\n\n  IHash\n  (-hash [coll] (caching-hash coll hash-coll __hash))\n\n  ISeqable\n  (-seq [coll]\n    (let [subvec-seq (fn subvec-seq [i]\n                       (when-not (== i end)\n                         (cons (-nth v i)\n                               (lazy-seq\n                                (subvec-seq (inc i))))))]\n      (subvec-seq start)))\n\n  ICounted\n  (-count [coll] (- end start))\n\n  IIndexed\n  (-nth [coll n]\n    (if (or (neg? n) (<= end (+ start n)))\n      (vector-index-out-of-bounds n (- end start))\n      (-nth v (+ start n))))\n  (-nth [coll n not-found]\n    (if (or (neg? n) (<= end (+ start n)))\n      not-found\n      (-nth v (+ start n) not-found)))\n\n  ILookup\n  (-lookup [coll k] (-nth coll k nil))\n  (-lookup [coll k not-found] (-nth coll k not-found))\n\n  IAssociative\n  (-assoc [coll key val]\n    (let [v-pos (+ start key)]\n      (build-subvec meta (assoc v v-pos val)\n               start (max end (inc v-pos))\n               nil)))\n\n  IVector\n  (-assoc-n [coll n val] (-assoc coll n val))\n\n  IReduce\n  (-reduce [coll f]\n    (ci-reduce coll f))\n  (-reduce [coll f start]\n    (ci-reduce coll f start))\n\n  IFn\n  (-invoke [coll k]\n    (-nth coll k))\n  (-invoke [coll k not-found]\n    (-nth coll k not-found)))",
          :title "Source code",
          :repo "clojurescript",
          :tag "r1859",
          :filename "src/cljs/cljs/core.cljs",
          :lines [3420 3499]},
 :full-name "cljs.core/Subvec",
 :full-name-encode "cljs.core/Subvec",
 :history [["+" "0.0-927"]]}

```

Retrieve the API data for this symbol:

```clj
;; from Clojure REPL
(require '[clojure.edn :as edn])
(-> (slurp "https://raw.githubusercontent.com/cljsinfo/cljs-api-docs/catalog/cljs-api.edn")
    (edn/read-string)
    (get-in [:symbols "cljs.core/Subvec"]))
```

-->