/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SuchazapisTestModule } from '../../../test.module';
import { TraitSuchazDetailComponent } from '../../../../../../main/webapp/app/entities/trait-suchaz/trait-suchaz-detail.component';
import { TraitSuchazService } from '../../../../../../main/webapp/app/entities/trait-suchaz/trait-suchaz.service';
import { TraitSuchaz } from '../../../../../../main/webapp/app/entities/trait-suchaz/trait-suchaz.model';

describe('Component Tests', () => {

    describe('TraitSuchaz Management Detail Component', () => {
        let comp: TraitSuchazDetailComponent;
        let fixture: ComponentFixture<TraitSuchazDetailComponent>;
        let service: TraitSuchazService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [SuchazapisTestModule],
                declarations: [TraitSuchazDetailComponent],
                providers: [
                    TraitSuchazService
                ]
            })
            .overrideTemplate(TraitSuchazDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TraitSuchazDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TraitSuchazService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new TraitSuchaz(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.trait).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
