class Solution {
public:
    int leftborder = -2;
    int rightborder = -2;
    vector<int> searchRange(vector<int>& nums, int target) {
        leftborder = getleftborder(nums, target);
        rightborder = getrightborder(nums, target);
        if (leftborder == -2 || rightborder == -2) {
            return {-1, -1};
        } else if (rightborder > leftborder + 1) {
            return {leftborder + 1, rightborder - 1};
        } else {
            return {-1, -1};
        }
    }

private:
    int getleftborder(vector<int>& nums, int target) {
        int left = 0;
        int right = nums.size() - 1;
        int leftborder = -2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) {
                right = mid - 1;
                leftborder = right;
            } else {
                left = mid + 1;
            }
        }
        return leftborder;
    }

    int getrightborder(vector<int>& nums, int target) {
        int left = 0;
        int right = nums.size() - 1;
        int rightborder = -2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target >= nums[mid]) {
                left = mid + 1;
                rightborder = left;
            } else {
                right = mid - 1;
            }
        }
        return rightborder;
    }
};