import re

filename = "D3_input.txt"
f = open(filename, "r")

cache = []

_sum = 0

counted_numbers = {}


def add_if_possible(line_pos, number_start, inc):
    _id = str(line_pos) + "_" + str(number_start)
    if counted_numbers.get(_id) is None:
        counted_numbers[_id] = inc
        return inc
    return 0


for line_index, line in enumerate(f):
    cache.append(line)
    if len(cache) == 3:
        for index, cache_line in enumerate(cache):
            symbols = re.finditer("[^0-9.\n]", cache_line)
            for symbol in symbols:
                symbol_pos = symbol.start()

                # line above
                if index != 0:
                    numbers = re.finditer("\\d+", cache[index - 1])
                    for n in numbers:
                        if n.start() - 1 <= symbol_pos <= n.end():
                            _sum += add_if_possible(line_index + index - 1, n.start(), int(n.group()))
                # this line
                numbers = re.finditer("\\d+", cache[index])
                for n in numbers:
                    if n.start() - 1 == symbol_pos or n.end() == symbol_pos:
                        _sum += add_if_possible(line_index + index, n.start(), int(n.group()))

                # line below
                if index != len(cache) - 1:
                    numbers = re.finditer("\\d+", cache[index + 1])
                    for n in numbers:
                        if n.start() - 1 <= symbol_pos <= n.end():
                            _sum += add_if_possible(line_index + index + 1, n.start(), int(n.group()))

        cache.pop(0)


isNotPart = set()
for line in open(filename, "r"):
    numbers = re.findall("\\d+", line)
    for number in numbers:
        found = False
        for el in counted_numbers.values():
            if el == int(number):
                found = True
                break
        if not found:
            isNotPart.add(number)

print(isNotPart)

print(_sum)
print(counted_numbers)
