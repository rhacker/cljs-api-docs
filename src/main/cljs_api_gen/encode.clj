(ns cljs-api-gen.encode
  (:refer-clojure :exclude [replace])
  (:require
    [clojure.string :refer [replace]]))

;;--------------------------------------------------------------------------------
;; Our custom encoding scheme for symbols <-> filenames
;;--------------------------------------------------------------------------------

(def encoding
  {"."  "DOT"
   ">"  "GT"
   "<"  "LT"
   "!"  "BANG"
   "?"  "QMARK"
   "*"  "STAR"
   "+"  "PLUS"
   "="  "EQ"
   "/"  "SLASH"})


(defn fullname->ns-name
  "fullname has a special meaning:

  foo     <-- we consider a standalone symbol a namespace
  foo/bar <-- normal qualified symbol

  return [ns name] pair for the given 'fullname'
  "
  [fullname]
  (let [[ns- name-] ((juxt namespace name) (symbol fullname))
        qualified? (not (nil? ns-))]

    (if qualified?
      [ns- name-]
      [name- nil])))

(defn encode-name [name-]
  (reduce (fn [s [a b]] (replace s a b))
    (name name-) encoding))

(defn decode-name [name-]
  (reduce (fn [s [a b]] (replace s b a))
    (name name-) encoding))

(defn decode-fullname
  [fullname]
  (let [[ns- name-] (fullname->ns-name fullname)
        decoded (cond-> ns-
                  name- (str "/" (decode-name name-)))]
    decoded))

(defn encode-fullname
  [fullname]
  (let [[ns- name-] (fullname->ns-name fullname)
        encoded (cond-> ns-
                  name- (str "/" (encode-name name-)))]
    encoded))

(defn assert-lossless
  [fullname]
  (let [encoded (encode-fullname fullname)
        decoded (decode-fullname encoded)]
    (assert (= decoded fullname)
            (str "Symbol name was lost during encoding and decoding:\n"
                 "  " fullname "  ->  " encoded "  ->  " decoded))))


;;--------------------------------------------------------------------------------
;; Markdown encoding helpers and others
;;--------------------------------------------------------------------------------

(defn md-escape
  [sym]
  (when sym
    (-> sym
        (replace "\\" "\\\\")
        (replace "*" "\\*")
        (replace "[" "\\[")
        (replace "]" "\\]"))))

(defn md-link-escape
  [s]
  (replace s ">" "%3E"))

(defn md-strikethru
  [s]
  (str "~~" s "~~"))

(defn md-header-link
  [s]
  (-> s
      (replace #"[^a-zA-Z0-9 ]" "")
      (replace " " "-")))

(defn shield-escape
  [s]
  (-> s
      (replace "-" "--")))


(def emoji-url
  "emoji table here: http://apps.timwhitlock.info/emoji/tables/unicode"
  {":heavy_check_mark:" "http://i.imgur.com/JfULGnn.png"
   ":no_entry_sign:"    "http://i.imgur.com/sWBgjc6.png"})

(defn fix-emoji
  "github currently disables emoji-rendering for large readmes, so just process them here."
  [s]
  (reduce
    (fn [s emoji]
      (replace s emoji
        (str "<img width=\"20px\" height=\"20px\" valign=\"middle\" src=\"" (emoji-url emoji) "\">")))
    s (keys emoji-url)))
