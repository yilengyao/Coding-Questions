import math
def finalInstances(instances, averageUtil):
    """
    :type instances: int
    :type averageUtil: List[int]
    :rtype: int
    """
    time = 0
    n = len(averageUtil)
    
    while time < n:
        old_instances = instances
        if averageUtil[time] < 25:
            instances = math.ceil(instances/2)
        elif averageUtil[time] > 60 and instances <= 10**8:
            instances = instances * 2
        if old_instances != instances:
            time = time + 10
        time = time + 1
    return instances