package com.wushengju.java;

/**
 * 红黑树实践
 *
 * @author Sunny
 * @create 2017-05-23 14:51
 **/
public class RBTree <T extends Comparable<T>>{

    /**
     * 根节点
     */
    private RBTreeNode<T> rootNode;
    /**
     * 颜色常量 false表示红色，true表示黑色
     */
    private static final boolean COLOR_RED = false;
    private static final boolean COLOR_BLACK = true;

    /**
     * 判断节点的颜色是否为红色
     * @param node
     * @return
     */
    private boolean isRed(RBTreeNode<T> node){
        return checkColor(node,COLOR_RED);
    }

    /**
     * 判断节点颜色是否为黑色
     * @param node
     * @return
     */
    private boolean isBlack(RBTreeNode<T> node){
        return checkColor(node,COLOR_BLACK);
    }

    private boolean checkColor(RBTreeNode<T> node, boolean color){
        return (node != null && node.color == color) ? true : false;
    }

    /**
     * 获取当前节点的父节点
     * @param node
     * @return
     */
    private RBTreeNode getParent(RBTreeNode<T> node){
        return (node != null) ? node.parent : null;
    }

    /**
     * 设置当前节点颜色为红色
     * @param node
     */
    private void setColorRed(RBTreeNode<T> node){
        setColor(node, COLOR_RED);
    }

    /**
     * 设置当前节点颜色为黑色
     * @param node
     */
    private void setColorBlack(RBTreeNode<T> node){
        setColor(node, COLOR_BLACK);
    }

    /**
     * 设置当前节点为指定颜色
     * @param node
     * @param color
     */
    private void setColor(RBTreeNode<T> node,boolean color){
        if (null != node){
            node.color = color;
        }
    }

    /**
     * 获取当前节点的颜色
     * @param node
     * @return
     */
    private boolean getColor(RBTreeNode<T> node){
        if (null != node){
            return node.color;
        }
        return false;
    }
    /**
     * 设置当前节点的父节点
     * @param node
     * @param parent
     */
    private void setParent(RBTreeNode<T> node,RBTreeNode<T> parent){
        if (null != node){
            node.parent = parent;
        }
    }

    /**
     * 左旋
     * @param node
     */
    private void leftRotate(RBTreeNode<T> node){
        RBTreeNode<T> rightChild = node.right;

        //将node的右孩子节点指向其右孩子节点的左孩子节点
        node.right = rightChild.left;

        //将右孩子节点的左孩子节点的父亲指向node
        if(null != rightChild.left){
            rightChild.left.parent = node;
        }

        //将node的右孩子节点的父亲指向node节点的父亲
        rightChild.parent = node.parent;

        //如果node是根节点，则将根节点设置为其右孩子节点
        if (null == node.parent){
            this.rootNode = rightChild;
        } else {
            //若node是其父节点的左孩子节点
            if (node.parent.left == node){
                node.parent.left = rightChild;
            } else {//若node是其父节点的右孩子节点
                node.parent.right = rightChild;
            }
        }
        //将node设置的右孩子节点的左节点
        rightChild.left = node;

        node.parent = rightChild;
    }

    /**
     * 右旋
     * @param node
     */
    private void rightRotate(RBTreeNode<T> node){
        //获取当前节点左孩子节点
        RBTreeNode<T> leftChild =  node.left;

        //当前节点左孩子引用指向其左孩子节点的右孩子节点
        node.left = leftChild.right;
        //当前节点的左孩子节点的右孩子节点不为null
        if (null != leftChild.right){
            //其左孩子节点的右孩子节点的父亲指向当前节点
            leftChild.right.parent = node;
        }

        //将前node的父亲设置为其左孩子的父亲
        leftChild.parent = node.parent;

        //如果当前的node是根节点，则将其左孩子设置为根节点
        if (null == node.parent){
            this.rootNode = leftChild;
        } else {
            //若不是，则设置node的左孩子为node父亲节点的左或右节点
            if(node == node.parent.right){
                node.parent.right = leftChild;
            } else {
                node.parent.left = leftChild;
            }
        }

        //设置node为其左孩子的右节点
        leftChild.right = node;
        //设置node的父节点为其左孩子的
        node.parent = leftChild;
    }
    /**
     * 获取根节点
     * @return
     */
    public RBTreeNode<T> root(){
        return null;
    }

    /**
     * 向红黑树中插入一个节点
     * @param key
     */
    public void insert(T key){
        RBTreeNode<T> node = new RBTreeNode<T>(COLOR_BLACK,key,null,null,null);
        if(null != node){
            insert(node);
        }
    }

    /**
     * 插入一个节点
     * @param node
     */
    private void insert(RBTreeNode<T> node){
        int cmp;
        RBTreeNode<T> root = this.rootNode;
        RBTreeNode<T> parent = null;

        //定位节点添加到哪个父节点下
        while(null != root){
            parent = root;
            cmp = node.key.compareTo(root.key);
            if (cmp < 0){
                root = root.left;
            } else {
                root = root.right;
            }
        }

        node.parent = parent;
        //表示当前没一个节点，那么就当新增的节点为根节点
        if (null == parent){
            this.rootNode = node;
        } else {
            //找出在当前父节点下新增节点的位置
            cmp = node.key.compareTo(parent.key);
            if (cmp < 0){
                parent.left = node;
            } else {
                parent.right = node;
            }
        }

        //设置插入节点的颜色为红色
        node.color = COLOR_RED;

        //修正为红黑树
        insertFixUp(node);
    }

    /**
     * 红黑树插入修正
     * @param node
     */
    private void insertFixUp(RBTreeNode<T> node){
        RBTreeNode<T> parent,gparent;
        //节点的父节点存在并且为红色
        while( ((parent = getParent(node)) != null) && isRed(parent)){
            gparent = getParent(parent);

            //如果其祖父节点是空怎么处理
            // 若父节点是祖父节点的左孩子
            if(parent == gparent.left){
                RBTreeNode<T> uncle = gparent.right;
                if ((null != uncle) && isRed(uncle)){
                    setColorBlack(uncle);
                    setColorBlack(parent);
                    setColorRed(gparent);
                    node = gparent;
                    continue;
                }

                if (parent.right == node){
                    RBTreeNode<T> tmp;
                    leftRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                setColorBlack(parent);
                setColorRed(gparent);
                rightRotate(gparent);
            } else {
                RBTreeNode<T> uncle = gparent.left;
                if ((null != uncle) && isRed(uncle)){
                    setColorBlack(uncle);
                    setColorBlack(parent);
                    setColorRed(gparent);
                    node = gparent;
                    continue;
                }

                if (parent.left == node){
                    RBTreeNode<T> tmp;
                    rightRotate(parent);
                    tmp = parent;
                    parent = node;
                    node = tmp;
                }

                setColorBlack(parent);
                setColorRed(gparent);
                leftRotate(gparent);
            }
        }
        setColorBlack(this.rootNode);
    }
    /**
     * 删除红黑树种的一个节点
     * @param key
     * @return
     */
    public boolean remove(T key){
        RBTreeNode<T> node = search(key);
        if (null != node){
            remove(node);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 内部调用的方法
     * @param node
     */
    private void remove(RBTreeNode<T> node){
        RBTreeNode<T> child,parent;
        boolean color;

        //被删除节点左右孩子都不为空的情况
        if ((null != node.left) && (null != node.right)){

            //获取到被删除节点的后继节点
            RBTreeNode<T> replace = node;

            replace = replace.right;
            while(null != replace.left){
                replace = replace.left;
            }

            //node节点不是根节点
            if (null != getParent(node)){
                //node是左节点
                if (getParent(node).left == node){
                    getParent(node).left = replace;
                } else {
                    getParent(node).right = replace;
                }
            } else {
                this.rootNode = replace;
            }

            child = replace.right;
            parent = getParent(replace);
            color = getColor(replace);

            if (parent == node){
                parent = replace;
            } else {
                if (null != child){
                    setParent(child,parent);
                }
                parent.left = child;

                replace.right = node.right;
                setParent(node.right, replace);
            }

            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;
            if (color == COLOR_BLACK){
                removeFixUp(child,parent);
            }

            node = null;
            return;
        }

        if (null != node.left){
            child = node.left;
        } else {
            child = node.right;
        }

        parent = node.parent;
        color = node.color;
        if (null != child){
            child.parent = parent;
        }

        if (null != parent){
            if (parent.left == node){
                parent.left = child;
            } else {
                parent.right = child;
            }
        } else {
            this.rootNode = child;
        }

        if (color == COLOR_BLACK){
            removeFixUp(child, parent);
        }
        node = null;
    }

    /**
     * 删除修复
     * @param node
     * @param parent
     */
    private void removeFixUp(RBTreeNode<T> node, RBTreeNode<T> parent){
        RBTreeNode<T> other;
        //node不为空且为黑色，并且不为根节点
        while ((null == node || isBlack(node)) && (node != this.rootNode) ){
            //node是父节点的左孩子
            if (node == parent.left){
                //获取到其右孩子
                other = parent.right;
                //node节点的兄弟节点是红色
                if (isRed(other)){
                    setColorBlack(other);
                    setColorRed(parent);
                    leftRotate(parent);
                    other = parent.right;
                }

                //node节点的兄弟节点是黑色，且兄弟节点的两个孩子节点也是黑色
                if ((other.left == null || isBlack(other.left)) &&
                        (other.right == null || isBlack(other.right))){
                    setColorRed(other);
                    node = parent;
                    parent = getParent(node);
                } else {
                    //node节点的兄弟节点是黑色，且兄弟节点的右孩子是红色
                    if (null == other.right || isBlack(other.right)){
                        setColorBlack(other.left);
                        setColorRed(other);
                        rightRotate(other);
                        other = parent.right;
                    }
                    //node节点的兄弟节点是黑色，且兄弟节点的右孩子是红色，左孩子是任意颜色
                    setColor(other, getColor(parent));
                    setColorBlack(parent);
                    setColorBlack(other.right);
                    leftRotate(parent);
                    node = this.rootNode;
                    break;
                }
            } else {
                other = parent.left;
                if (isRed(other)){
                    setColorBlack(other);
                    setColorRed(parent);
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((null == other.left || isBlack(other.left)) &&
                        (null == other.right || isBlack(other.right))){
                    setColorRed(other);
                    node = parent;
                    parent = getParent(node);
                } else {
                    if (null == other.left || isBlack(other.left)){
                        setColorBlack(other.right);
                        setColorRed(other);
                        leftRotate(other);
                        other = parent.left;
                    }

                    setColor(other,getColor(parent));
                    setColorBlack(parent);
                    setColorBlack(other.left);
                    rightRotate(parent);
                    node = this.rootNode;
                    break;
                }
            }
        }
        if (node!=null)
            setColorBlack(node);
    }

    /**
     * 从节点node开始查找 为key的节点
     * @param node
     * @param key
     * @return
     */
    private RBTreeNode<T> search(RBTreeNode<T> node,T key){
        if(null == node) {
            return null;
        }
        int tmp = key.compareTo(node.key);
        if (tmp < 0){
            return search(node.left,key);
        } else if (tmp > 0){
            return search(node.right,key);
        } else {
            return node;
        }
    }

    /**
     * 搜索对应的key
     * @param key
     * @return
     */
    public RBTreeNode<T> search(T key){
        return search(this.rootNode,key);
    }

    /**
     * 节点内部类
     * @param <T>
     */
    public class RBTreeNode<T extends Comparable<T>>{

        /**
         * 颜色 true表示黑，false表示红
         */
        private boolean color;
        /**
         * 用于比较的key值
         */
        private T key;
        /**
         * 指向父节点的引用
         */
        private RBTreeNode<T> parent;
        /**
         * 指向左孩子节点的引用
         */
        private RBTreeNode<T> left;
        /**
         * 指向右孩子节点的引用
         */
        private RBTreeNode<T> right;

        public RBTreeNode(boolean color, T key, RBTreeNode<T> parent, RBTreeNode<T> left, RBTreeNode<T> right) {
            this.color = color;
            this.key = key;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }
}
