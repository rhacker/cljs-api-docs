## <img width="48px" valign="middle" src="http://i.imgur.com/Hi20huC.png"> cljs.core/undefined?

 <table border="1">
<tr>
<td>function</td>
<td><a href="https://github.com/cljsinfo/api-refs/tree/0.0-927"><img valign="middle" alt="[+] 0.0-927" src="https://img.shields.io/badge/+-0.0--927-lightgrey.svg"></a> </td>
</tr>
</table>

 <samp>
(__undefined?__ x)<br>
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
            └── <ins>[core.cljs:815-816](https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L815-L816)</ins>
</pre>

```clj
(defn ^boolean undefined? [x]
  (cljs.core/undefined? x))
```


---

 <pre>
clojurescript @ r1211
└── src
    └── clj
        └── cljs
            └── <ins>[core.clj:99-100](https://github.com/clojure/clojurescript/blob/r1211/src/clj/cljs/core.clj#L99-L100)</ins>
</pre>

```clj
(defmacro undefined? [x]
  (bool-expr (list 'js* "(void 0 === ~{})" x)))
```

---

```clj
{:return-type boolean,
 :ns "cljs.core",
 :name "undefined?",
 :signature ["[x]"],
 :shadowed-sources ({:code "(defmacro undefined? [x]\n  (bool-expr (list 'js* \"(void 0 === ~{})\" x)))",
                     :filename "clojurescript/src/clj/cljs/core.clj",
                     :lines [99 100],
                     :link "https://github.com/clojure/clojurescript/blob/r1211/src/clj/cljs/core.clj#L99-L100"}),
 :history [["+" "0.0-927"]],
 :type "function",
 :full-name-encode "cljs.core_undefined_QMARK_",
 :source {:code "(defn ^boolean undefined? [x]\n  (cljs.core/undefined? x))",
          :filename "clojurescript/src/cljs/cljs/core.cljs",
          :lines [815 816],
          :link "https://github.com/clojure/clojurescript/blob/r1211/src/cljs/cljs/core.cljs#L815-L816"},
 :full-name "cljs.core/undefined?"}

```