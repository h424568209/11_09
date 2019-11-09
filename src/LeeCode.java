import java.util.Arrays;

public class LeeCode {
    public int search(int[] nums, int target) {
       return search(nums,0,nums.length-1,target);
    }

    private int search(int[] nums, int low, int high, int target) {
        if(low>high){
            return -1;
        }
        int mid = (low+high)/2;
        if(nums[mid] == target){
            return mid;
        }
        if(nums[mid]<nums[high]){
            if(nums[mid]<target && target<nums[high])
            return search(nums,mid,high,target);
            else
                return search(nums,low,mid-1,target);
        }else{
            if(nums[mid]>target && target>nums[low]){
                return search(nums,low,mid-1,target);
            }else{
                return search(nums,mid+1,high,target);
            }
        }

    }

    /**
     * 将给定数字序列重新排列成字典序中下一个更大的排列。
     * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）
     * 从后向前进行遍历，遇到前一个比它大的停止，记为A，后在A的后面找最小的大于它的数B，A、B进行交换
     * 最后将A后面的序列全部逆置
     * @param nums 给定的数字序列
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length-2;
            while(0<=n&&nums[n+1]<=nums[n]){
                n--;
            }
            if(n>=0){
                int j = nums.length-1;
                while(j>=0 && nums[j]<=nums[n]){
                    j--;
                }
                swap(nums,j,n);
            }
            reverse(nums,n+1);
    }

    private void reverse(int[] nums, int i) {
        int n = nums.length-1;
        while(i<n){
            swap(nums,i,n);
            n--;
            i++;
        }
    }

    private void swap(int[] nums, int n, int i) {
        int t = nums[n];
        nums[n] = nums[i];
        nums[i] = t;
    }

    /**
     *  使用递归进行寻早两个有序数组的中位数
     * @param nums1 有序数组1
     * @param nums2 有序数组2
     * @return  两个有序数组的中位数
     */
    //O(longN 复杂度)
    public double findMedianSortedArraysplus(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left =(n+m+1)/2;
        int right = (n+2+m)/2;
        //将偶数和奇数两个情况合并，如果是奇数，就会求两次同样的k
        return (getLen(nums1,0,n-1,nums2,0,m-1,left)+getLen(nums1,0,n-1,nums2,0,m-1,right))/2.0;
    }

    private int getLen(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2,int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 +1;
        //令len1 的长度小于len2的长度，这样保证如果有数组空了，则一定是len1
        if(len1>len2){
            return getLen(nums2,start2,end2,nums1,start1,end1,k);
        }
        if(len1==0)
            return nums2[start2+k-1];
        if(k==1){
            return Math.min(nums1[0],nums2[0]);
        }
        int i = start1 + Math.min(len1,k/2)-1;
        int j = start2 + Math.min(len2,k/2)-1;
        //若两个数组长度允许，比较k/2位置的大小，后将小的k/2直接移除比较的序列
        //若长度不允许，则进行比较最大长度的和k/2位置的大小，将小的全部移除
        if(nums1[i]>nums2[j]){
            return getLen(nums1,start1,end1,nums2,j+1,end2,k-(j-start2+1));
        }else{
            return getLen(nums1,i+1,end1,nums2,start2,end2,k-(i-start1+1));
        }
    }

    //O(n)复杂度
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int []res = new int[nums1.length+nums2.length];
        int i = 0 ;
        int k = 0 ;
        for(;i< nums1.length;i++){
            res[k++] = nums1[i];
        }
        for(int j = 0 ; j < nums2.length; j++){
            res[k++] = nums2[j];
        }
        Arrays.sort(res);
        if(res.length%2 == 0){
            return (res[res.length/2]+res[res.length/2-1])/2.0;
        }else{
            return res[res.length/2];
        }
    }

    public static void main(String[] args) {
        int []nums1= {1,3};
        int []nums2 = {2};
        LeeCode l = new LeeCode();
        System.out.println(l.findMedianSortedArrays(nums1,nums2));
        int[]num = {1,5,8,4,7,6,5,3,1};
        l.nextPermutation(num);
        System.out.println(Arrays.toString(num));
    }
}
