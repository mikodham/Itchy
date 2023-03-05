names = ["mosquito4"] 

# Read .obj 3d model file
for name in names:
    verts = []
    norms = []
    texts = []
    faces = []
    
    file = open(f'{name}.obj', 'r')
    lines = file.readlines()

    # extract vertices, norms, and faces
    for line in lines:
        line = line.strip("\n")
        args = line.strip("\n").split(' ')
        if args[0] == 'v':
            verts.append((float(args[1]), float(args[2]), float(args[3])))
        elif args[0] == 'vn':
            norms.append((float(args[1]), float(args[2]), float(args[3])))
        elif args[0] == 'vt':
            texts.append((float(args[1]), float(args[2])))
        elif args[0] == 'f':
            faces.append((args[1].split("/"), args[2].split("/"), args[3].split("/")))

    verts_str = ""
    # write down all the coordinates of vertices in a file
    # f = open(f"{name}_verts.txt", "w")
    for face in faces:
        for vert_idx in face:
            vert_idx = int(vert_idx[0])
            vert_str = str(verts[vert_idx-1]).strip("(").replace(",", "f,").replace(")", "f, ")
            # f.write(verts_str)
            verts_str += vert_str
    # f.write("\n")
    # f.close()

    norms_str = ""
    # write down all the normals in another file
    # f = open(f"{name}_norms.txt", "w")
    for face in faces:
        for norm_idx in face:
            norm_idx = int(norm_idx[2])
            norm_str = str(norms[norm_idx-1]).strip("(").replace(",", "f,").replace(")", "f, ")
            # f.write(norm_str)
            norms_str += norm_str
    # f.close()
    
    texts_str = ""
    for face in faces:
        for text_idx in face:
            text_idx = int(text_idx[1])
            text_str = str(texts[text_idx-1]).strip("(").replace(",", "f,").replace(")", "f, ")
            # f.write(norm_str)
            texts_str += text_str
            
    f = open(f"{name}_copy_paste.txt", "w")
    # print(f"""
    #     private val verts = floatArrayOf(\n{verts_str})\n\n
    #     private val norms = floatArrayOf(\n{norms_str})\n\n
    #     private val texts = floatArrayOf(\n{texts_str})\n\n
    # """)
    f.write(f"""
// {name}.obj
private val verts = floatArrayOf({verts_str})
private val norms = floatArrayOf({norms_str})
private val texts = floatArrayOf({texts_str})
    """)
    f.close()