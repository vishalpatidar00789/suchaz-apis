import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ItemImageSuchaz } from './item-image-suchaz.model';
import { ItemImageSuchazPopupService } from './item-image-suchaz-popup.service';
import { ItemImageSuchazService } from './item-image-suchaz.service';

@Component({
    selector: 'jhi-item-image-suchaz-delete-dialog',
    templateUrl: './item-image-suchaz-delete-dialog.component.html'
})
export class ItemImageSuchazDeleteDialogComponent {

    itemImage: ItemImageSuchaz;

    constructor(
        private itemImageService: ItemImageSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemImageService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'itemImageListModification',
                content: 'Deleted an itemImage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-item-image-suchaz-delete-popup',
    template: ''
})
export class ItemImageSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itemImagePopupService: ItemImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.itemImagePopupService
                .open(ItemImageSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
