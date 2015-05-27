## <img width="48px" valign="middle" src="http://i.imgur.com/Hi20huC.png"> cljs.core/js-obj

 <table border="1">
<tr>
<td>function</td>
<td><a href="https://github.com/cljsinfo/api-refs/tree/0.0-927"><img valign="middle" alt="[+] 0.0-927" src="https://img.shields.io/badge/+-0.0--927-lightgrey.svg"></a> </td>
</tr>
</table>

 <samp>
(__js-obj__)<br>
(__js-obj__ & keyvals)<br>
</samp>

```
(no docstring)
```

---

 <pre>
clojurescript @ r1211
└── src
    └── cljs
        └── cljs
            └── <ins>[core.cljs:773-777](https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L773-L777)</ins>
</pre>

```clj
(defn js-obj
  ([]
     (js* "{}"))
  ([& keyvals]
     (apply gobject/create keyvals)))
```


---

 <pre>
clojurescript @ r1211
└── src
    └── clj
        └── cljs
            └── <ins>[core.clj:795-802](https://github.com/clojure/clojurescript/blob/r1211/src/clj/cljs/core.clj#L795-L802)</ins>
</pre>

```clj
(defmacro js-obj [& rest]
  (let [kvs-str (->> (repeat "~{}:~{}")
                     (take (quot (count rest) 2))
                     (interpose ",")
                     (apply core/str))]
    (concat
     (list 'js* (core/str "{" kvs-str "}"))
     rest)))
```

---

```clj
{:full-name "cljs.core/js-obj",
 :ns "cljs.core",
 :name "js-obj",
 :type "function",
 :signature ["[]" "[& keyvals]"],
 :source {:code "(defn js-obj\n  ([]\n     (js* \"{}\"))\n  ([& keyvals]\n     (apply gobject/create keyvals)))",
          :filename "clojurescript/src/cljs/cljs/core.cljs",
          :lines [773 777],
          :link "https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L773-L777"},
 :full-name-encode "cljs.core_js-obj",
 :shadowed-sources ({:code "(defmacro js-obj [& rest]\n  (let [kvs-str (->> (repeat \"~{}:~{}\")\n                     (take (quot (count rest) 2))\n                     (interpose \",\")\n                     (apply core/str))]\n    (concat\n     (list 'js* (core/str \"{\" kvs-str \"}\"))\n     rest)))",
                     :filename "clojurescript/src/clj/cljs/core.clj",
                     :lines [795 802],
                     :link "https://github.com/clojure/clojurescript/blob/r1211/src/clj/cljs/core.clj#L795-L802"}),
 :history [["+" "0.0-927"]]}

```