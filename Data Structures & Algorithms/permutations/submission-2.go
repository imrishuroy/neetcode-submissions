func permute(nums []int) [][]int {

    result := make([][]int, 0)
    path := make([]int, 0)
    used := make([]bool, len(nums))
    
    backtrack(nums, path, used, &result)

    return result;

}

func backtrack(nums []int, path []int, used []bool, result *[][]int) {
    if len(path) == len(nums) {
        temp := make([]int, len(nums))
        copy(temp, path)
        *result = append(*result, temp)
    }

        for i := 0; i < len(nums); i++ {
            if used[i] {
                continue
            }
            path = append(path, nums[i])
            used[i] = true;

            backtrack(nums, path, used, result)
            used[i] = false
            path = path[:len(path) - 1]
        }
}


