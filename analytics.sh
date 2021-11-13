for ifile in $(git ls-files);
	do
		echo 'File Name: ' $ifile
		for file in $(git ls-files $ifile); do git blame -c $file; done | tr '(' ' ' | awk '{print $2, $3}' | sort | uniq -c | sort -k1 -r;
done
