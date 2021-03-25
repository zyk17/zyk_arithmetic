package com.zhangsan.no_18_orderedMap;

/**
 * @author zhangsan
 * @date 2021/3/25 17:34
 */
public class Code01_AVLTreeMap {

    public static class AVLNode<K extends Comparable<K>, V> {
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

    public static class AVLTree<K extends Comparable<K>, V> {
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
            right.h = Math.max(right.l.h, right.r.h) + 1;
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

    }

}
