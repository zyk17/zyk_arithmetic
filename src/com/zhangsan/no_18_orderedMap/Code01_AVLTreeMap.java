package com.zhangsan.no_18_orderedMap;

/**
 * 平衡搜索二叉树
 * @author zhangsan
 * @date 2021/3/25 17:34
 */
public class Code01_AVLTreeMap {

    public static class AVLTree<K extends Comparable<K>, V> implements SortedMap<K, V> {
        private AVLNode<K, V> root;
        private int size;

        public AVLTree() {}

        /** 右旋, 返回新的头部 */
        private AVLNode<K, V> rightRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> left = cur.l;
            cur.l = left.r;
            left.r = cur;
            cur.h = Math.max(cur.l == null ? 0 : cur.l.h, cur.r == null ? 0 : cur.r.h) + 1;
            left.h = Math.max(left.l == null ? 0 : left.l.h, left.r.h) + 1;
            return left;
        }

        /** 左旋, 返回新的头部 */
        private AVLNode<K, V> leftRotate(AVLNode<K, V> cur) {
            AVLNode<K, V> right = cur.r;
            cur.r = right.l;
            right.l = cur;
            cur.h = Math.max(cur.l == null ? 0 : cur.l.h, cur.r == null ? 0 : cur.r.h) + 1;
            right.h = Math.max(right.l.h, right.r==null?0: right.r.h) + 1;
            return right;
        }

        /**
         * 添加节点: 搜索树应该不含有重复的key, 这里没有检查,要求上游调用需确保无重复key
         * @param cur 以哪棵树为头,添加节点
         * @param k 添加的k
         * @param v 添加的v
         * @return 添加后的这棵树的头节点.
         */
        private AVLNode<K, V> add(AVLNode<K, V> cur, K k, V v) {
            if(cur == null) {
                return new AVLNode<>(k, v);
            }else {
                if(k.compareTo(cur.k) < 0) {
                    cur.l = add(cur.l, k, v);
                }else {
                    cur.r = add(cur.r, k, v);
                }
                cur.h = Math.max(cur.l == null ? 0 : cur.l.h, cur.r == null ? 0 : cur.r.h) + 1;
                return maintain(cur);
            }
        }

        /**
         * 删除k的节点,
         * 确保要删除的k存在.
         * @param cur 当前树的头节点
         * @param k 要删除的k
         * @return 删除后这棵树的头节点
         */
        private AVLNode<K, V> delete(AVLNode<K, V> cur, K k) {
            // 左边
            if(k.compareTo(cur.k) < 0) {
                cur.l = delete(cur.l, k);
            }else if(k.compareTo(cur.k) > 0) {
                // 右边
                cur.r = delete(cur.r, k);
            }else {
                if( cur.l == null && cur.r==null ) {
                    cur = null;
                }else if(cur.l != null && cur.r == null) {
                    cur = cur.l;
                }else if(cur.l == null && cur.r != null) {
                    cur = cur.r;
                }else {
                    AVLNode<K, V> des = cur.r;
                    while (des.l != null) {
                        des = des.l;
                    }
                    // 找到替代节点了, 右数上最左节点, 不能直接指, 得先删除那个节点在指
                    cur.r = delete(cur.r, des.k);
                    des.l = cur.l;
                    des.r = cur.r;
                    cur = des;
                }
            }
            if(cur != null) {
                cur.h = Math.max(cur.l == null ? 0 : cur.l.h, cur.r == null ? 0 : cur.r.h) + 1;
            }
            return maintain(cur);
        }

        /** 维护这棵树, 使他保持平衡, 返回头节点 */
        private AVLNode<K,V> maintain(AVLNode<K,V> cur) {
            if(cur == null) {
                return null;
            }
            int lh = cur.l == null ? 0 : cur.l.h;
            int rh = cur.r == null ? 0 : cur.r.h;
            // 破坏平衡性了.
            if( Math.abs(lh-rh) > 1 ) {
                // 左边高
                if(lh > rh) {
                    int llh = cur.l != null && cur.l.l != null? cur.l.l.h: 0;
                    int lrh = cur.l != null && cur.l.r != null? cur.l.r.h: 0;
                    if(llh >= lrh) {
                        // llh || lrh(左树的左右子树一样高) 右旋解决
                        cur = rightRotate(cur);
                    }else {
                        // lrh 孙子节点到头部
                        cur.l = leftRotate(cur.l);
                        cur = rightRotate(cur);
                    }
                }else {
                    // 右边高
                    int rrh = cur.r != null && cur.r.r != null? cur.r.r.h: 0;
                    int rlh = cur.r != null && cur.r.l != null? cur.r.l.h: 0;
                    if(rrh >= rlh) {
                        // rrh || rlh(右树的左右子树一样高) 左旋解决
                        cur = leftRotate(cur);
                    }else {
                        // rlh 孙子节点到头部
                        cur.r = rightRotate(cur.r);
                        cur = leftRotate(cur);
                    }
                }
            }
            return cur;
        }

        private AVLNode<K, V> findLastIndex(K key) {
            AVLNode<K, V> cur = root;
            AVLNode<K, V> pre = null;
            while (cur != null) {
                pre = cur;
                if(key.compareTo(cur.k) == 0) {
                    break;
                }else if(key.compareTo(cur.k) < 0) {
                    cur = cur.l;
                }else {
                    cur = cur.r;
                }
            }
            return pre;
        }

        private AVLNode<K, V> findLastNoSmallIndex(K key) {
            AVLNode<K, V> cur = root;
            AVLNode<K, V> ans = null;
            while (cur != null) {
                if(key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                }else if(key.compareTo(cur.k) < 0) {
                    ans = cur;
                    cur = cur.l;
                }else {
                    cur = cur.r;
                }
            }
            return ans;
        }

        private AVLNode<K, V> findLastNoBigIndex(K key) {
            AVLNode<K, V> cur = root;
            AVLNode<K, V> ans = null;
            while (cur != null) {
                if(key.compareTo(cur.k) == 0) {
                    ans = cur;
                    break;
                }else if(key.compareTo(cur.k) < 0) {
                    cur = cur.l;
                }else {
                    ans = cur;
                    cur = cur.r;
                }
            }
            return ans;
        }

        @Override
        public int size() {
            return this.size;
        }

        @Override
        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && lastNode.k.compareTo(key) == 0;
        }

        @Override
        public void put(K key, V value) {
            if( key == null ) {
                return;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if( lastNode != null && lastNode.k.compareTo(key) == 0 ) {
                lastNode.v = value;
            }else {
                size++;
                this.root = add(this.root, key, value);
            }
        }

        @Override
        public void remove(K key) {
            if(key == null) {
                return;
            }
            if(containsKey(key)) {
                size--;
                this.root = delete(root, key);
            }
        }

        @Override
        public V get(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNode = findLastIndex(key);
            if (lastNode != null && key.compareTo(lastNode.k) == 0) {
                return lastNode.v;
            }
            return null;
        }

        @Override
        public K firstKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = this.root;
            while (cur.l != null) {
                cur = cur.l;
            }
            return cur.k;
        }

        @Override
        public K lastKey() {
            if (root == null) {
                return null;
            }
            AVLNode<K, V> cur = this.root;
            while (cur.r != null) {
                cur = cur.r;
            }
            return cur.k;
        }

        @Override
        public K floorKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
            return lastNoBigNode == null ? null : lastNoBigNode.k;
        }

        @Override
        public K ceilingKey(K key) {
            if (key == null) {
                return null;
            }
            AVLNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
            return lastNoSmallNode == null ? null : lastNoSmallNode.k;
        }


        private static class AVLNode<K extends Comparable<K>, V> {
            public K k;
            public V v;
            public AVLNode<K, V> l;
            public AVLNode<K, V> r;
            public int h;

            public AVLNode(K k, V v) {
                this.k = k;
                this.v = v;
                this.h = 1;
            }
        }

    }

    public static void main(String[] args) {
        SortedMap<Integer, String> sortedMap = new AVLTree<>();
        sortedMap.put(1, "我是1.");
        sortedMap.put(1, "我是1.0.");
        sortedMap.put(2, "我是2.");
        sortedMap.put(3, "我是3.");

        System.out.println("当前大小:" + sortedMap.size());
        System.out.println(sortedMap.get(1));
        System.out.println(sortedMap.get(2));
        System.out.println(sortedMap.get(4));
        sortedMap.remove(2);
        System.out.println("当前大小:" + sortedMap.size());
        sortedMap.remove(5);
        System.out.println(sortedMap.get(4));

        sortedMap.put(4, "我是4.");
        sortedMap.put(5, "我是5.");
        sortedMap.put(6, "我是6.");
        sortedMap.put(7, "我是7.");
        System.out.println(sortedMap.get(7));
        System.out.println("当前大小:" + sortedMap.size());
        System.out.println(sortedMap.firstKey());
        System.out.println(sortedMap.lastKey());
        System.out.println(sortedMap.floorKey(2));
    }

}
