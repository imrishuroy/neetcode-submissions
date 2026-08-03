func subsets(nums []int) [][]int {
    result := make([][]int, 0)
    path := make([]int, 0)
    backtrack(0, nums, path, &result)

    return result;
}

func backtrack(start int, nums []int, path []int, result *[][]int) {
    temp := make([]int, len(path))
    copy(temp, path)
    *result = append(*result, temp)

    for i := start; i < len(nums); i++ {
        path = append(path, nums[i])
        backtrack(i + 1, nums, path, result)
        path = path[:len(path) - 1]
    }
}
