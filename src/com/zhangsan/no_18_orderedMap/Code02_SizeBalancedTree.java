package com.zhangsan.no_18_orderedMap;

/**
 * SBT 平衡搜索二叉树
 *
 * AVL树是根据左子树和右子树的高度决定的即  abs(l.height - r.height) < 2 即平衡
 * SBT是根据侄子节点大小 <= 叔叔节点大小 及平衡
 *
 * @author zhangsan
 * @date 2021/3/26 13:09
 */
public class Code02_SizeBalancedTree {

    public static class SizeBalanced<K extends Comparable<K>, V> implements SortedMap<K, V> {
        private SBTNode<K, V> root;

        public SizeBalanced() {}

        /** 右旋 */
        private SBTNode<K, V> rightRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> left = cur.l;
            cur.l = left.r;
            left.r = cur;
            left.size = cur.size;
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
            return left;
        }
        /** 左旋 */
        private SBTNode<K, V> leftRotate(SBTNode<K, V> cur) {
            SBTNode<K, V> right = cur.r;
            cur.r = right.l;
            right.l = cur;
            right.size = cur.size;
            cur.size = (cur.l == null ? 0 : cur.l.size) + (cur.r == null ? 0 : cur.r.size) + 1;
            return right;
        }

        /**
         * 维护这棵树的平衡, 违规情况有四种 ll, lr, rl, rr
         * 通过旋转进行平衡, 并递归维护修改过节点数量的节点
         * @param cur 当前树
         * @return 这棵树平衡后新的头
         */
        private SBTNode<K, V> maintain(SBTNode<K, V> cur) {
            if (cur == null)
                return null;
            int ls = 0, lls = 0,lrs = 0, rs = 0, rls = 0,rrs = 0;
            if(cur.l != null) {
                ls = cur.l.size;
                lls = cur.l.l == null ? 0: cur.l.l.size;
                lrs = cur.l.r == null ? 0: cur.l.r.size;
            }
            if( cur.r != null ) {
                rs = cur.r.size;
                rls = cur.r.l == null? 0 : cur.r.l.size;
                rrs = cur.r.r == null? 0 : cur.r.r.size;
            }
            if( lls > rs ) {
                // lls一次右旋
                cur = rightRotate(cur);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }else if( lrs > rs ) {
                // lrs 孙子节点变爷爷
                cur.l = leftRotate(cur.l);
                cur = rightRotate(cur);
                // 变动的节点在调整从小往上调
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }else if( rrs > ls ) {
                // rrs一次左旋
                cur = leftRotate(cur);
                cur.l = maintain(cur.l);
                cur = maintain(cur);
            }else if(rls > ls) {
                // rls 孙子节点变爷爷
                cur.r = rightRotate(cur.r);
                cur = leftRotate(cur);
                // 变动的节点在调整从小往上调
                cur.l = maintain(cur.l);
                cur.r = maintain(cur.r);
                cur = maintain(cur);
            }
            return cur;
        }

        /**
         * 添加节点
         * @param cur 在哪儿棵树上添加
         * @param k 添加的key
         * @param v 添加的value
         * @return 返回添加后当前这棵树维护平衡后的新头部
         */
        private SBTNode<K, V> add(SBTNode<K, V> cur, K k, V v) {
            if(cur == null) {
                return new SBTNode<>(k, v);
            }else {
                cur.size++;
                if(k.compareTo(cur.k) < 0) {
                    cur.l = add(cur.l, k, v);
                }else {
                    cur.r = add(cur.r, k, v);
                }
                return maintain(cur);
            }
        }

        /**
         * 删除节点, 调用函数需确保要删除的key存在
         * @param cur 当前树
         * @param k 要删除的key
         * @return 删除节点后并维护好平衡后新的头部
         */
        private SBTNode<K, V> delete(SBTNode<K, V> cur, K k) {
            // 左边
            cur.size--;
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
                    SBTNode<K, V> pre = null;
                    SBTNode<K, V> des = cur.r;
                    des.size--;
                    while (des.l != null) {
                        pre = des;
                        des = des.l;
                        des.size--;
                    }
                    if(pre != null) {
                        // 一路向左找的
                        // 没有递归删除的动作, 所以倒数第二个节点要连好替代节点的右节点
                        pre.l = des.r;
                        des.r = cur.r;
                    }
                    des.l = cur.l;
                    des.size = des.l.size + (des.r == null ? 0 : des.r.size) + 1;
                    cur = des;
                }
            }
//            return maintain(cur);
            return cur;
        }

        /** 找到目标key,如果没有返回历他最近的那个节点 */
        private SBTNode<K, V> findLastIndex(K key) {
            SBTNode<K, V> cur = root;
            SBTNode<K, V> pre = null;
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

        /** 返回大于等于最接近key的节点 */
        private SBTNode<K, V> findLastNoSmallIndex(K key) {
            SBTNode<K, V> cur = root;
            SBTNode<K, V> ans = null;
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

        /** 返回小于等于最接近key的节点 */
        private SBTNode<K, V> findLastNoBigIndex(K key) {
            SBTNode<K, V> cur = root;
            SBTNode<K, V> ans = null;
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

        /** 获取第i个因为有size可以计算出位置, 如果是avl想实现这个功能可以中序遍历计算位置 */
        private SBTNode<K, V> getIndex(SBTNode<K, V> cur, int kth) {
            if (kth == (cur.l != null ? cur.l.size : 0) + 1) {
                return cur;
            } else if (kth <= (cur.l != null ? cur.l.size : 0)) {
                return getIndex(cur.l, kth);
            } else {
                return getIndex(cur.r, kth - (cur.l != null ? cur.l.size : 0) - 1);
            }
        }

        @Override
        public int size() {
            return root == null ? 0 : root.size;
        }

        @Override
        public boolean containsKey(K key) {
            if (key == null) {
                return false;
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && lastNode.k.compareTo(key) == 0;
        }

        @Override
        public void put(K key, V value) {
            if(key == null) {
                return;
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            if(lastNode != null && lastNode.k.compareTo(key) == 0) {
                lastNode.v = value;
            }else {
                root = add(root, key, value);
            }
        }

        @Override
        public void remove(K key) {
            if(key == null) {
                return;
            }
            if(containsKey(key)) {
                root = delete(root, key);
            }
        }

        @Override
        public V get(K key) {
            if(key == null) {
                return null;
            }
            SBTNode<K, V> lastNode = findLastIndex(key);
            return lastNode != null && lastNode.k.compareTo(key) == 0? lastNode.v: null;
        }

        public K getIndexKey(int index) {
            if (index < 0 || index >= this.size()) {
                throw new RuntimeException("invalid parameter.");
            }
            return getIndex(root, index + 1).k;
        }

        public V getIndexValue(int index) {
            if (index < 0 || index >= this.size()) {
                throw new RuntimeException("invalid parameter.");
            }
            return getIndex(root, index + 1).v;
        }

        @Override
        public K firstKey() {
            if (root == null) {
                return null;
            }
            SBTNode<K, V> cur = root;
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
            SBTNode<K, V> cur = root;
            while (cur.r != null) {
                cur = cur.r;
            }
            return cur.k;
        }

        @Override
        public K floorKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> lastNoBigNode = findLastNoBigIndex(key);
            return lastNoBigNode == null ? null : lastNoBigNode.k;
        }

        @Override
        public K ceilingKey(K key) {
            if (key == null) {
                throw new RuntimeException("invalid parameter.");
            }
            SBTNode<K, V> lastNoSmallNode = findLastNoSmallIndex(key);
            return lastNoSmallNode == null ? null : lastNoSmallNode.k;
        }

        /** SB树的节点 */
        public static class SBTNode<K, V> {
            public K k;
            public V v;
            public SBTNode<K, V> l;
            public SBTNode<K, V> r;
            public int size; // 不同的节点的数量

            public SBTNode(K key, V value) {
                this.k = key;
                this.v = value;
                size = 1;
            }
        }

    }

    // for test
    public static void main(String[] args) {
        SortedMap<Integer, String> sortedMap = new SizeBalanced<>();
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
