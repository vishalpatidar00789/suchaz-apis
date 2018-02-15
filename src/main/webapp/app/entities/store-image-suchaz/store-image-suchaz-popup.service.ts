import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { StoreImageSuchaz } from './store-image-suchaz.model';
import { StoreImageSuchazService } from './store-image-suchaz.service';

@Injectable()
export class StoreImageSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private storeImageService: StoreImageSuchazService

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
                this.storeImageService.find(id)
                    .subscribe((storeImageResponse: HttpResponse<StoreImageSuchaz>) => {
                        const storeImage: StoreImageSuchaz = storeImageResponse.body;
                        this.ngbModalRef = this.storeImageModalRef(component, storeImage);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.storeImageModalRef(component, new StoreImageSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    storeImageModalRef(component: Component, storeImage: StoreImageSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.storeImage = storeImage;
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
