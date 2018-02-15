import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ItemReviewSuchaz } from './item-review-suchaz.model';
import { ItemReviewSuchazPopupService } from './item-review-suchaz-popup.service';
import { ItemReviewSuchazService } from './item-review-suchaz.service';

@Component({
    selector: 'jhi-item-review-suchaz-delete-dialog',
    templateUrl: './item-review-suchaz-delete-dialog.component.html'
})
export class ItemReviewSuchazDeleteDialogComponent {

    itemReview: ItemReviewSuchaz;

    constructor(
        private itemReviewService: ItemReviewSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.itemReviewService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'itemReviewListModification',
                content: 'Deleted an itemReview'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-item-review-suchaz-delete-popup',
    template: ''
})
export class ItemReviewSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private itemReviewPopupService: ItemReviewSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.itemReviewPopupService
                .open(ItemReviewSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
