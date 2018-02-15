import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { CategoryImageSuchaz } from './category-image-suchaz.model';
import { CategoryImageSuchazService } from './category-image-suchaz.service';

@Injectable()
export class CategoryImageSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private categoryImageService: CategoryImageSuchazService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.categoryImageService.find(id)
                    .subscribe((categoryImageResponse: HttpResponse<CategoryImageSuchaz>) => {
                        const categoryImage: CategoryImageSuchaz = categoryImageResponse.body;
                        this.ngbModalRef = this.categoryImageModalRef(component, categoryImage);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.categoryImageModalRef(component, new CategoryImageSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    categoryImageModalRef(component: Component, categoryImage: CategoryImageSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.categoryImage = categoryImage;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
