import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { StoreImageSuchaz } from './store-image-suchaz.model';
import { StoreImageSuchazPopupService } from './store-image-suchaz-popup.service';
import { StoreImageSuchazService } from './store-image-suchaz.service';

@Component({
    selector: 'jhi-store-image-suchaz-delete-dialog',
    templateUrl: './store-image-suchaz-delete-dialog.component.html'
})
export class StoreImageSuchazDeleteDialogComponent {

    storeImage: StoreImageSuchaz;

    constructor(
        private storeImageService: StoreImageSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.storeImageService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'storeImageListModification',
                content: 'Deleted an storeImage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-store-image-suchaz-delete-popup',
    template: ''
})
export class StoreImageSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private storeImagePopupService: StoreImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.storeImagePopupService
                .open(StoreImageSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
