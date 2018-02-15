import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { HobbyImageSuchaz } from './hobby-image-suchaz.model';
import { HobbyImageSuchazService } from './hobby-image-suchaz.service';

@Injectable()
export class HobbyImageSuchazPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private hobbyImageService: HobbyImageSuchazService

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
                this.hobbyImageService.find(id)
                    .subscribe((hobbyImageResponse: HttpResponse<HobbyImageSuchaz>) => {
                        const hobbyImage: HobbyImageSuchaz = hobbyImageResponse.body;
                        this.ngbModalRef = this.hobbyImageModalRef(component, hobbyImage);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.hobbyImageModalRef(component, new HobbyImageSuchaz());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    hobbyImageModalRef(component: Component, hobbyImage: HobbyImageSuchaz): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.hobbyImage = hobbyImage;
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
