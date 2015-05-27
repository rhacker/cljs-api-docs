## <img width="48px" valign="middle" src="http://i.imgur.com/Hi20huC.png"> cljs.core/bit-and-not

 <table border="1">
<tr>
<td>function</td>
<td><a href="https://github.com/cljsinfo/api-refs/tree/0.0-927"><img valign="middle" alt="[+] 0.0-927" src="https://img.shields.io/badge/+-0.0--927-lightgrey.svg"></a> </td>
<td>
[<img height="24px" valign="middle" src="http://i.imgur.com/1GjPKvB.png"> <samp>clojure.core/bit-and-not</samp>](http://clojure.github.io/clojure/branch-master/clojure.core-api.html#clojure.core/bit-and-not)
</td>
</tr>
</table>

 <samp>
(__bit-and-not__ x y)<br>
</samp>

```
Bitwise and
```

---

 <pre>
clojurescript @ r1211
└── src
    └── cljs
        └── cljs
            └── <ins>[core.cljs:1160-1162](https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L1160-L1162)</ins>
</pre>

```clj
(defn bit-and-not
  [x y] (cljs.core/bit-and-not x y))
```


---

 <pre>
clojurescript @ r1211
└── src
    └── clj
        └── cljs
            └── <ins>[core.clj:210-212](https://github.com/clojure/clojurescript/blob/r1211/src/clj/cljs/core.clj#L210-L212)</ins>
</pre>

```clj
(defmacro bit-and-not
  ([x y] (list 'js* "(~{} & ~~{})" x y))
  ([x y & more] `(bit-and-not (bit-and-not ~x ~y) ~@more)))
```

---

```clj
{:ns "cljs.core",
 :name "bit-and-not",
 :signature ["[x y]"],
 :shadowed-sources ({:code "(defmacro bit-and-not\n  ([x y] (list 'js* \"(~{} & ~~{})\" x y))\n  ([x y & more] `(bit-and-not (bit-and-not ~x ~y) ~@more)))",
                     :filename "clojurescript/src/clj/cljs/core.clj",
                     :lines [210 212],
                     :link "https://github.com/clojure/clojurescript/blob/r1211/src/clj/cljs/core.clj#L210-L212"}),
 :history [["+" "0.0-927"]],
 :type "function",
 :full-name-encode "cljs.core_bit-and-not",
 :source {:code "(defn bit-and-not\n  [x y] (cljs.core/bit-and-not x y))",
          :filename "clojurescript/src/cljs/cljs/core.cljs",
          :lines [1160 1162],
          :link "https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L1160-L1162"},
 :full-name "cljs.core/bit-and-not",
 :clj-symbol "clojure.core/bit-and-not",
 :docstring "Bitwise and"}

```