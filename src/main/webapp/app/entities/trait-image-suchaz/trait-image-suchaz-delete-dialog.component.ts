import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TraitImageSuchaz } from './trait-image-suchaz.model';
import { TraitImageSuchazPopupService } from './trait-image-suchaz-popup.service';
import { TraitImageSuchazService } from './trait-image-suchaz.service';

@Component({
    selector: 'jhi-trait-image-suchaz-delete-dialog',
    templateUrl: './trait-image-suchaz-delete-dialog.component.html'
})
export class TraitImageSuchazDeleteDialogComponent {

    traitImage: TraitImageSuchaz;

    constructor(
        private traitImageService: TraitImageSuchazService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.traitImageService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'traitImageListModification',
                content: 'Deleted an traitImage'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-trait-image-suchaz-delete-popup',
    template: ''
})
export class TraitImageSuchazDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private traitImagePopupService: TraitImageSuchazPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.traitImagePopupService
                .open(TraitImageSuchazDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
